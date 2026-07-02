// JavaScript Exercises Implementation

// --- Exercise 1: Basics & Setup ---
window.addEventListener('DOMContentLoaded', () => {
    console.log("Welcome to the Community Portal (Exercise 1 Debug log)");
    alert("Welcome to the JavaScript Interactive Portal!");
    initPortal();
});

// Global state holding event list
let eventList = [];

// --- Exercise 5: Objects & Prototypes ---
class CommunityEvent {
    constructor(id, name, category, date, seats = 20) { // Exercise 10: Default parameters
        this.id = id;
        this.name = name;
        this.category = category;
        this.date = date;
        this.seats = seats; // Exercise 2: let equivalent within object context
    }
    
    // Prototype method
    checkAvailability() {
        return this.seats > 0;
    }
}

// --- Exercise 4: Closures ---
// Tracker closure function that counts total registrations in a specific category
function createCategoryRegistrationTracker() {
    let registrationCounts = {};
    return {
        increment: function(category) {
            registrationCounts[category] = (registrationCounts[category] || 0) + 1;
            return registrationCounts[category];
        },
        getCounts: function() {
            return { ...registrationCounts }; // return a copy
        }
    };
}
const regTracker = createCategoryRegistrationTracker();

// --- Exercise 9: Async JS, Promises, Async/Await ---
// Fetch events from mock JSON endpoint with a loading spinner demo
async function fetchMockEvents() {
    const spinner = document.getElementById('loadingSpinner');
    const container = document.getElementById('eventsGrid');
    
    spinner.style.display = 'block';
    container.innerHTML = '';
    
    try {
        // Fetch from local JSON file
        const response = await fetch('events.json');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        
        // Exercise 3: Use try-catch, map results to class objects
        // Exercise 6: Array method mapper
        eventList = data.map(item => new CommunityEvent(item.id, item.name, item.category, item.date, item.seats));
        console.log("Loaded event objects successfully (Exercise 13 Debug):", eventList);
        
        renderEvents(eventList);
    } catch (error) {
        console.error("Error loading events (Exercise 3/9):", error);
        container.innerHTML = `<p class="error-msg">Failed to load events: ${error.message}</p>`;
    } finally {
        spinner.style.display = 'none';
    }
}

// --- Exercise 7: DOM Manipulation & Rendering ---
function renderEvents(events) {
    const container = document.getElementById('eventsGrid');
    container.innerHTML = '';
    
    if (events.length === 0) {
        container.innerHTML = '<p style="color: var(--text-muted);">No events match your criteria.</p>';
        return;
    }
    
    // Exercise 3: Loop through event list using forEach()
    events.forEach(event => {
        // Exercise 10: Destructuring details
        const { id, name, category, date, seats } = event;
        
        // Exercise 7: Create and append elements
        const card = document.createElement('div');
        card.className = `event-card event-cat-${category}`;
        card.id = `event-card-${id}`;
        
        // Exercise 2: Template literals for formatting content
        const availText = event.checkAvailability() ? `${seats} seats available` : 'Sold Out';
        const availClass = seats === 0 ? 'seats-full' : (seats < 10 ? 'seats-low' : 'seats-ok');
        
        card.innerHTML = `
            <div>
                <div class="event-meta">
                    <span>${date}</span>
                    <span class="badge badge-${category}">${category}</span>
                </div>
                <h3 class="event-title">${name}</h3>
                <p class="event-desc">Experience this wonderful community program.</p>
            </div>
            <div>
                <p class="event-seats ${availClass}">${availText}</p>
                <button class="btn btn-register-card" onclick="registerUser(${id})" ${seats === 0 ? 'disabled' : ''}>
                    ${seats === 0 ? 'Sold Out' : 'Register Now'}
                </button>
            </div>
        `;
        
        container.appendChild(card);
    });
    
    updateSummary();
}

// --- Exercise 4 & 6: Higher-Order Functions & Callbacks ---
// Filter events matching a custom criteria callback
function filterEvents(eventsList, filterCallback) {
    // Exercise 10: Spread operator to clone list before filtering
    const clone = [...eventsList];
    return clone.filter(filterCallback);
}

// Event handlers triggered by UI dropdown filter (Exercise 8)
function applyFilters() {
    const categoryValue = document.getElementById('categoryFilter').value;
    const nameSearch = document.getElementById('nameSearch').value.toLowerCase();
    
    // Callback logic combining category and search matching
    const matchesFilter = (evt) => {
        const matchesCategory = (categoryValue === 'all' || evt.category === categoryValue);
        const matchesName = evt.name.toLowerCase().includes(nameSearch);
        return matchesCategory && matchesName;
    };
    
    const filtered = filterEvents(eventList, matchesFilter);
    renderEvents(filtered);
}

// Special button filter to show only music events (Exercise 6)
function showOnlyMusic() {
    const musicEvents = filterEvents(eventList, e => e.category === 'music');
    renderEvents(musicEvents);
}

// --- Exercise 2 & 3 & 4: Seat management, Closures, Try-Catch Registration ---
function registerUser(eventId) {
    // Find the event
    const event = eventList.find(e => e.id === eventId);
    
    try {
        if (!event) {
            throw new Error("Event not found.");
        }
        if (!event.checkAvailability()) {
            throw new Error("Sorry, this event has no seats left!");
        }
        
        // Exercise 2: Manage seat count via -- operator
        event.seats--;
        
        // Increment category registrations closure (Exercise 4)
        const totalCategoryRegs = regTracker.increment(event.category);
        console.log(`Updated registration statistics: ${event.category} category now has ${totalCategoryRegs} registrations.`);
        
        // Update DOM element details dynamically (Exercise 7)
        renderEvents(eventList);
        
        // Exercise 14: Use jQuery to animate registered notice
        $('#registeredBanner').text(`Success! Registered for ${event.name}. Category total registrations: ${totalCategoryRegs}`).fadeIn().delay(3000).fadeOut();
        
    } catch (error) {
        console.error("Registration error:", error.message);
        alert(`Failed to register: ${error.message}`);
    }
}

// Create custom event from form submit (Exercise 11)
function handleAddEvent(e) {
    e.preventDefault();
    
    const form = document.getElementById('addEventForm');
    const nameInput = form.elements['eventName'];
    const dateInput = form.elements['eventDate'];
    const categorySelect = form.elements['eventCategory'];
    const seatsInput = form.elements['eventSeats'];
    
    let isValid = true;
    
    // Reset errors
    document.querySelectorAll('.error-msg').forEach(el => el.textContent = '');
    
    // Validations
    if (!nameInput.value.trim()) {
        document.getElementById('nameError').textContent = 'Event name is required.';
        isValid = false;
    }
    if (!dateInput.value) {
        document.getElementById('dateError').textContent = 'Event date is required.';
        isValid = false;
    }
    
    if (!isValid) return;
    
    // Exercise 6: Push new event
    const newId = eventList.length + 1;
    const newEvent = new CommunityEvent(
        newId,
        nameInput.value,
        categorySelect.value,
        dateInput.value,
        parseInt(seatsInput.value) || 20
    );
    
    eventList.push(newEvent);
    console.log("Added new event (Exercise 6 .push()):", newEvent);
    
    renderEvents(eventList);
    form.reset();
}

// --- Exercise 12: AJAX / Fetch API POST mock ---
function submitRegistrationForm(e) {
    e.preventDefault();
    
    const form = document.getElementById('userRegistrationForm');
    const name = form.elements['userName'].value;
    const email = form.elements['userEmail'].value;
    
    const statusText = document.getElementById('formSubmitStatus');
    statusText.textContent = "Submitting registration details...";
    statusText.className = '';
    
    // Simulate Fetch POST with a timeout (Exercise 12)
    setTimeout(() => {
        // Construct payload
        const payload = { name, email, submissionDate: new Date() };
        console.log("AJAX/Fetch payload posted (Exercise 13 check):", payload);
        
        // Mock successful API response
        statusText.textContent = `Thank you, ${name}! Your registration was processed successfully.`;
        statusText.className = 'seats-ok';
        form.reset();
    }, 1500);
}

// Render summary stats using Object.entries() (Exercise 5)
function updateSummary() {
    const summaryBox = document.getElementById('summaryStats');
    if (!summaryBox) return;
    
    const counts = regTracker.getCounts();
    
    let html = '<strong>Active Registrations by Category: </strong>';
    
    // Use Object.entries() to display key-value pairs
    const entries = Object.entries(counts);
    if (entries.length === 0) {
        html += 'No registrations yet.';
    } else {
        entries.forEach(([category, count]) => {
            html += `<span style="margin: 0 10px;">${category}: <strong>${count}</strong></span>`;
        });
    }
    summaryBox.innerHTML = html;
}

// Init Function
function initPortal() {
    fetchMockEvents();
    
    // Event listeners
    document.getElementById('categoryFilter').addEventListener('change', applyFilters);
    
    // Exercise 8: keydown search event
    document.getElementById('nameSearch').addEventListener('keyup', applyFilters);
    
    document.getElementById('addEventForm').addEventListener('submit', handleAddEvent);
    document.getElementById('userRegistrationForm').addEventListener('submit', submitRegistrationForm);
}
