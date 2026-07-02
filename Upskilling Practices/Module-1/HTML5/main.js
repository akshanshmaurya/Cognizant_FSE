// HTML5 Exercises Implementation JS

// 1. Page Load Event Handler (Exercise 1.1 Alert)
window.addEventListener('DOMContentLoaded', () => {
    console.log("Welcome to the Community Portal (Exercise 10 Debug Log)");
    // Alert user on load
    alert("Welcome to the Local Community Event Portal!");
    
    // 8. Retrieve User Preferences from Local Storage (Exercise 8)
    const savedEventType = localStorage.getItem('preferredEventType');
    if (savedEventType) {
        const selectElem = document.getElementById('eventType');
        if (selectElem) {
            selectElem.value = savedEventType;
            // Trigger fee update matching selection
            updateEventFee(savedEventType);
        }
    }
});

// Flag to track if form is modified for onbeforeunload warning
let isFormDirty = false;

function markFormDirty() {
    isFormDirty = true;
}

// 7. Warn user if trying to leave with unsaved form changes (Exercise 7)
window.addEventListener('beforeunload', (e) => {
    if (isFormDirty) {
        const confirmationMessage = 'You have unsaved event registration form changes. Are you sure you want to leave?';
        (e || window.event).returnValue = confirmationMessage; // Standard
        return confirmationMessage; // WebKit
    }
});

// 6. Validate phone number on blur (Exercise 6)
function validatePhoneNumber() {
    const phoneInput = document.getElementById('phone');
    const phoneError = document.getElementById('phoneError');
    const phonePattern = /^[0-9]{10}$/; // Basic 10-digit number validation
    
    if (phoneInput.value && !phonePattern.test(phoneInput.value)) {
        phoneError.textContent = "Please enter a valid 10-digit phone number.";
        phoneInput.classList.add('error');
    } else {
        phoneError.textContent = "";
        phoneInput.classList.remove('error');
    }
}

// Update Event Fee based on dropdown change (Exercise 6 & 8)
function updateEventFee(eventType) {
    const feeDisplay = document.getElementById('eventFee');
    let fee = "$0.00 (Free)";
    
    switch (eventType) {
        case 'tech':
            fee = "$15.00";
            break;
        case 'sports':
            fee = "$10.00";
            break;
        case 'arts':
            fee = "$20.00";
            break;
        case 'charity':
            fee = "Free";
            break;
    }
    
    feeDisplay.textContent = `Selected Event Fee: ${fee}`;
    
    // Save selection to Local Storage (Exercise 8)
    localStorage.setItem('preferredEventType', eventType);
}

// Triggered when dropdown changes (Exercise 6 onchange)
function onEventTypeChange() {
    const selectElem = document.getElementById('eventType');
    updateEventFee(selectElem.value);
    markFormDirty();
}

// Form Submission output and confirmation (Exercise 5 & 6)
function handleFormSubmit(event) {
    event.preventDefault(); // Prevent actual submission page refresh
    
    const name = document.getElementById('fullName').value;
    const email = document.getElementById('email').value;
    const date = document.getElementById('eventDate').value;
    const type = document.getElementById('eventType').value;
    const outputElem = document.getElementById('confirmationOutput');
    
    // Display confirmation output using <output>
    outputElem.innerHTML = `Registration Confirmed!<br>Name: ${name}<br>Email: ${email}<br>Date: ${date}<br>Type: ${type}`;
    
    // Also display browser confirmation pop-up (Exercise 6 onclick)
    alert("Thank you! Your registration has been submitted successfully.");
    
    // Reset dirty flag since form is submitted
    isFormDirty = false;
    
    // Demo session storage for registration time
    sessionStorage.setItem('lastRegisteredTime', new Date().toLocaleTimeString());
}

// Feedback text character counter (Exercise 6 keyboard events)
function countCharacters() {
    const feedbackText = document.getElementById('feedbackText');
    const charCounter = document.getElementById('charCounter');
    charCounter.textContent = `Characters: ${feedbackText.value.length}`;
}

// 7. Video oncanplay event handler (Exercise 7)
function handleVideoCanPlay() {
    const videoStatus = document.getElementById('videoStatus');
    videoStatus.textContent = "Video invite status: Ready to play!";
    console.log("Media Event: Video can play now.");
}

// 8. Clear preferences (Exercise 8)
function clearPreferences() {
    localStorage.removeItem('preferredEventType');
    sessionStorage.removeItem('lastRegisteredTime');
    
    // Reset select form element
    document.getElementById('eventType').value = 'tech';
    updateEventFee('tech');
    
    alert("Preferences and Session data cleared!");
}

// 9. Geolocation implementation (Exercise 9)
function findNearbyEvents() {
    const geoStatus = document.getElementById('geoStatus');
    
    if (!navigator.geolocation) {
        geoStatus.textContent = "Geolocation is not supported by your browser.";
        return;
    }
    
    geoStatus.textContent = "Locating nearest events...";
    
    const geoOptions = {
        enableHighAccuracy: true,
        timeout: 10000,
        maximumAge: 0
    };
    
    navigator.geolocation.getCurrentPosition(
        (position) => {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;
            geoStatus.innerHTML = `Nearest Event Coordinates:<br>Latitude: <strong>${lat.toFixed(6)}</strong><br>Longitude: <strong>${lon.toFixed(6)}</strong>`;
            console.log(`User coordinates: Lat ${lat}, Lon ${lon}`);
        },
        (error) => {
            let errorMsg = "Error obtaining location: ";
            switch (error.code) {
                case error.PERMISSION_DENIED:
                    errorMsg += "Permission denied by user.";
                    break;
                case error.POSITION_UNAVAILABLE:
                    errorMsg += "Location information is unavailable.";
                    break;
                case error.TIMEOUT:
                    errorMsg += "Location request timed out.";
                    break;
                default:
                    errorMsg += "Unknown error occurred.";
            }
            geoStatus.textContent = errorMsg;
            console.error(errorMsg);
        },
        geoOptions
    );
}

// Image Double Click to Enlarge (Exercise 6 ondblclick)
function enlargeImage(imgElement) {
    const modal = document.getElementById('imgModal');
    const modalImg = document.getElementById('modalImg');
    modal.style.display = "flex";
    modalImg.src = imgElement.src;
    modalImg.alt = imgElement.alt;
}

function closeModal() {
    document.getElementById('imgModal').style.display = "none";
}
