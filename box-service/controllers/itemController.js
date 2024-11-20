const { store, saveStore } = require('../data/store');

const loadItems = (req, res) => {
  const { id } = req.params;
  const { items } = req.body;

  const box = store.boxes.find((b) => b.id === parseInt(id));
  if (!box) return res.status(404).json({ error: 'Box not found.' });

  if (box.batteryCapacity < 25) {
    return res.status(400).json({ error: 'Battery level too low for loading.' });
  }

  const totalWeight = items.reduce((sum, item) => sum + item.weight, 0);
  if (totalWeight > box.weightLimit) {
    return res.status(400).json({ error: 'Weight exceeds box limit.' });
  }

  items.forEach((item) => {
    if (!/^[a-zA-Z0-9-_]+$/.test(item.name)) {
      return res.status(400).json({ error: `Invalid name: ${item.name}` });
    }
    if (!/^[A-Z0-9_]+$/.test(item.code)) {
      return res.status(400).json({ error: `Invalid code: ${item.code}` });
    }
  });

  const loadedItems = items.map((item) => ({ ...item, boxId: parseInt(id) }));
  store.items.push(...loadedItems);

  box.state = 'LOADING';
  saveStore(store); // Save changes to file

  return res.status(200).json({ message: 'Items loaded successfully.', items: loadedItems });
};

const getLoadedItems = (req, res) => {
    const { id } = req.params;
  
    const items = store.items.filter((item) => item.boxId === parseInt(id));
    if (!items.length) return res.status(404).json({ error: 'No items found for this box.' });
  
    return res.status(200).json(items);
  };

module.exports = {
    loadItems,
    getLoadedItems,
};