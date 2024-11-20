const fs = require('fs');
const path = require('path');

const storeFilePath = path.join(__dirname, 'store.json');

// Function to load store from JSON file
function loadStore() {
  if (fs.existsSync(storeFilePath)) {
    const data = fs.readFileSync(storeFilePath, 'utf8');
    return JSON.parse(data);
  }
  // Default store structure if the file doesn't exist
  return { boxes: [], items: [] };
}

// Function to save store to JSON file
function saveStore(store) {
  fs.writeFileSync(storeFilePath, JSON.stringify(store, null, 2), 'utf8');
}

// Initialize store
const store = loadStore();

module.exports = {
    store,
    loadStore,
    saveStore,
 };