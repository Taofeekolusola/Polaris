const { store, saveStore } = require('../data/store');
const { loadStore } = require('../data/store');
const createBox = (req, res) => {
  const { txref, batteryCapacity } = req.body;

  // Validate input
  if (!txref || txref.length > 20) {
    return res.status(400).json({ error: 'Invalid txref (max 20 characters).' });
  }

  if (!batteryCapacity || batteryCapacity < 0 || batteryCapacity > 100) {
    return res.status(400).json({ error: 'Battery capacity must be between 0 and 100.' });
  }

  // Create new box
  const newBox = {
    id: store.boxes.length + 1,
    txref,
    weightLimit: 500,
    batteryCapacity,
    state: 'IDLE',
  };

  store.boxes.push(newBox);
  saveStore(store); // Save changes to file

  return res.status(201).json(newBox);
};

const getAvailableBoxes = (req, res) => {

  // Load the current store from the JSON file
  const store = loadStore();

  // Filter boxes where the state is 'IDLE' or 'RETURNING'
  const availableBoxes = store.boxes.filter(
    (box) => box.state === 'IDLE' || box.state === 'RETURNING'
  );

  if (!availableBoxes.length) {
    return res.status(404).json({ error: 'No available boxes found.' });
  }

  return res.status(200).json(availableBoxes);
};
  
const getBatteryLevel = (req, res) => {
    const { id } = req.params;
  
    const box = store.boxes.find((b) => b.id === parseInt(id));
    if (!box) return res.status(404).json({ error: 'Box not found.' });
  
    return res.status(200).json({ batteryCapacity: box.batteryCapacity });
  };

module.exports = {
    createBox,
    getAvailableBoxes,
    getBatteryLevel,
};