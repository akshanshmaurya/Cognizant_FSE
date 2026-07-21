-- PL/SQL Deep Skilling Mandatory Exercises
-- Target Database: Oracle DB / PL/SQL Engine

-- ==========================================
-- 0. SCHEMA CREATION (DDL) & SAMPLE DATA
-- ==========================================

DROP TABLE AuditLog;
DROP TABLE Transactions;
DROP TABLE Loans;
DROP TABLE Accounts;
DROP TABLE Customers;
DROP TABLE Employees;

CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE,
    IsVIP CHAR(1) DEFAULT 'F'
);

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
);

CREATE TABLE AuditLog (
    LogID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TransactionID NUMBER,
    AccountID NUMBER,
    LogDate DATE,
    Amount NUMBER,
    Action VARCHAR2(50)
);

-- Sample Data Insertion
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'), 12000, SYSDATE);
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (1, 1, 'Savings', 1000, SYSDATE);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (2, 2, 'Checking', 1500, SYSDATE);

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (1, 1, SYSDATE, 200, 'Deposit');
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 1));
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (2, 2, 10000, 7, SYSDATE, ADD_MONTHS(SYSDATE, 60));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));
INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

COMMIT;


-- ==========================================
-- EXERCISE 1: CONTROL STRUCTURES
-- ==========================================

-- Scenario 1: Apply 1% discount to loan interest rates for customers over 60 years old.
DECLARE
    v_age NUMBER;
BEGIN
    FOR rec IN (SELECT l.LoanID, c.DOB, l.InterestRate FROM Loans l JOIN Customers c ON l.CustomerID = c.CustomerID) LOOP
        v_age := MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12;
        IF v_age > 60 THEN
            UPDATE Loans SET InterestRate = InterestRate - 1 WHERE LoanID = rec.LoanID;
            DBMS_OUTPUT.PUT_LINE('Discount applied to Loan ID: ' || rec.LoanID);
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Promote customer to VIP status if balance > $10,000.
BEGIN
    FOR rec IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers SET IsVIP = 'T' WHERE CustomerID = rec.CustomerID;
            DBMS_OUTPUT.PUT_LINE('Customer ID ' || rec.CustomerID || ' promoted to VIP.');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Send reminders for loans due within next 30 days.
BEGIN
    FOR rec IN (SELECT l.LoanID, c.Name, l.EndDate FROM Loans l JOIN Customers c ON l.CustomerID = c.CustomerID WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30) LOOP
        DBMS_OUTPUT.PUT_LINE('REMINDER: Loan ID ' || rec.LoanID || ' for customer ' || rec.Name || ' is due on ' || TO_CHAR(rec.EndDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/


-- ==========================================
-- EXERCISE 2: ERROR HANDLING
-- ==========================================

-- Scenario 1: SafeTransferFunds with exception handling and rollback.
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_acc NUMBER,
    p_to_acc NUMBER,
    p_amount NUMBER
) IS
    v_bal NUMBER;
    e_insufficient_funds EXCEPTION;
BEGIN
    SELECT Balance INTO v_bal FROM Accounts WHERE AccountID = p_from_acc FOR UPDATE;
    IF v_bal < p_amount THEN
        RAISE e_insufficient_funds;
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_acc;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_acc;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer successful!');
EXCEPTION
    WHEN e_insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in Account ' || p_from_acc);
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error during transfer: ' || SQLERRM);
END;
/

-- Scenario 2: UpdateSalary handling non-existent employee ID.
CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_emp_id NUMBER,
    p_percentage NUMBER
) IS
    e_no_emp EXCEPTION;
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count FROM Employees WHERE EmployeeID = p_emp_id;
    IF v_count = 0 THEN
        RAISE e_no_emp;
    END IF;

    UPDATE Employees SET Salary = Salary * (1 + p_percentage/100) WHERE EmployeeID = p_emp_id;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for Employee ID: ' || p_emp_id);
EXCEPTION
    WHEN e_no_emp THEN
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_emp_id || ' does not exist.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- Scenario 3: AddNewCustomer preventing duplicate insertion.
CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_id NUMBER,
    p_name VARCHAR2,
    p_dob DATE,
    p_balance NUMBER
) IS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer added successfully.');
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_id || ' already exists.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


-- ==========================================
-- EXERCISE 3: STORED PROCEDURES
-- ==========================================

-- Scenario 1: ProcessMonthlyInterest for savings accounts.
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    UPDATE Accounts SET Balance = Balance * 1.01 WHERE AccountType = 'Savings';
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to Savings accounts.');
END;
/

-- Scenario 2: UpdateEmployeeBonus by department.
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_dept VARCHAR2,
    p_bonus_pct NUMBER
) IS
BEGIN
    UPDATE Employees SET Salary = Salary * (1 + p_bonus_pct/100) WHERE Department = p_dept;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus applied to department: ' || p_dept);
END;
/

-- Scenario 3: TransferFunds checking balance.
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_acc NUMBER,
    p_to_acc NUMBER,
    p_amount NUMBER
) IS
    v_bal NUMBER;
BEGIN
    SELECT Balance INTO v_bal FROM Accounts WHERE AccountID = p_from_acc;
    IF v_bal >= p_amount THEN
        UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_acc;
        UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_acc;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Transfer completed.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Insufficient balance.');
    END IF;
END;
/


-- ==========================================
-- EXERCISE 4: FUNCTIONS
-- ==========================================

-- Scenario 1: CalculateAge
CREATE OR REPLACE FUNCTION CalculateAge (p_dob DATE) RETURN NUMBER IS
BEGIN
    RETURN TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END;
/

-- Scenario 2: CalculateMonthlyInstallment
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount NUMBER,
    p_interest_rate NUMBER,
    p_years NUMBER
) RETURN NUMBER IS
    v_monthly_rate NUMBER;
    v_months NUMBER;
    v_installment NUMBER;
BEGIN
    v_monthly_rate := (p_interest_rate / 100) / 12;
    v_months := p_years * 12;
    IF v_monthly_rate = 0 THEN
        RETURN p_loan_amount / v_months;
    END IF;
    v_installment := (p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_months)) / (POWER(1 + v_monthly_rate, v_months) - 1);
    RETURN ROUND(v_installment, 2);
END;
/

-- Scenario 3: HasSufficientBalance
CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_acc_id NUMBER,
    p_amount NUMBER
) RETURN BOOLEAN IS
    v_bal NUMBER;
BEGIN
    SELECT Balance INTO v_bal FROM Accounts WHERE AccountID = p_acc_id;
    RETURN v_bal >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/


-- ==========================================
-- EXERCISE 5: TRIGGERS
-- ==========================================

-- Scenario 1: UpdateCustomerLastModified
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

-- Scenario 2: LogTransaction
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, LogDate, Amount, Action)
    VALUES (:NEW.TransactionID, :NEW.AccountID, SYSDATE, :NEW.Amount, :NEW.TransactionType);
END;
/

-- Scenario 3: CheckTransactionRules
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_bal NUMBER;
BEGIN
    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_bal FROM Accounts WHERE AccountID = :NEW.AccountID;
        IF :NEW.Amount > v_bal THEN
            RAISE_APPLICATION_ERROR(-20001, 'Withdrawal amount exceeds available account balance.');
        END IF;
    ELSIF :NEW.TransactionType = 'Deposit' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
        END IF;
    END IF;
END;
/


-- ==========================================
-- EXERCISE 6: CURSORS
-- ==========================================

-- Scenario 1: GenerateMonthlyStatements
DECLARE
    CURSOR c_trans IS
        SELECT t.TransactionID, t.AccountID, t.Amount, t.TransactionType, a.CustomerID
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM');
BEGIN
    FOR rec IN c_trans LOOP
        DBMS_OUTPUT.PUT_LINE('Statement - Customer: ' || rec.CustomerID || ' | Acc: ' || rec.AccountID || ' | Type: ' || rec.TransactionType || ' | Amount: $' || rec.Amount);
    END LOOP;
END;
/

-- Scenario 2: ApplyAnnualFee
DECLARE
    CURSOR c_accounts IS
        SELECT AccountID, Balance FROM Accounts FOR UPDATE;
    v_fee CONSTANT NUMBER := 50;
BEGIN
    FOR rec IN c_accounts LOOP
        UPDATE Accounts SET Balance = Balance - v_fee WHERE CURRENT OF c_accounts;
        DBMS_OUTPUT.PUT_LINE('Annual fee deducted from Account ID: ' || rec.AccountID);
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: UpdateLoanInterestRates
DECLARE
    CURSOR c_loans IS
        SELECT LoanID, InterestRate FROM Loans FOR UPDATE;
BEGIN
    FOR rec IN c_loans LOOP
        UPDATE Loans SET InterestRate = InterestRate + 0.5 WHERE CURRENT OF c_loans;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('All loan interest rates updated by +0.5%.');
END;
/


-- ==========================================
-- EXERCISE 7: PACKAGES
-- ==========================================

-- Scenario 1: CustomerManagement Package
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER);
    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER);
    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
    END AddCustomer;

    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER) IS
    BEGIN
        UPDATE Customers SET Name = p_name, Balance = p_balance WHERE CustomerID = p_id;
        COMMIT;
    END UpdateCustomer;

    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER IS
        v_bal NUMBER;
    BEGIN
        SELECT Balance INTO v_bal FROM Customers WHERE CustomerID = p_id;
        RETURN v_bal;
    END GetCustomerBalance;
END CustomerManagement;
/

-- Scenario 2: EmployeeManagement Package
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_pos VARCHAR2, p_salary NUMBER, p_dept VARCHAR2);
    FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_pos VARCHAR2, p_salary NUMBER, p_dept VARCHAR2) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_id, p_name, p_pos, p_salary, p_dept, SYSDATE);
        COMMIT;
    END HireEmployee;

    FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER IS
        v_sal NUMBER;
    BEGIN
        SELECT Salary INTO v_sal FROM Employees WHERE EmployeeID = p_id;
        RETURN v_sal * 12;
    END CalculateAnnualSalary;
END EmployeeManagement;
/

-- Scenario 3: AccountOperations Package
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_acc_id NUMBER, p_cust_id NUMBER, p_type VARCHAR2, p_bal NUMBER);
    PROCEDURE CloseAccount(p_acc_id NUMBER);
    FUNCTION GetTotalBalance(p_cust_id NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(p_acc_id NUMBER, p_cust_id NUMBER, p_type VARCHAR2, p_bal NUMBER) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_acc_id, p_cust_id, p_type, p_bal, SYSDATE);
        COMMIT;
    END OpenAccount;

    PROCEDURE CloseAccount(p_acc_id NUMBER) IS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_acc_id;
        COMMIT;
    END CloseAccount;

    FUNCTION GetTotalBalance(p_cust_id NUMBER) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total FROM Accounts WHERE CustomerID = p_cust_id;
        RETURN v_total;
    END GetTotalBalance;
END AccountOperations;
/
