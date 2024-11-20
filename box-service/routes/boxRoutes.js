const express = require('express');
const router = express.Router();
const boxController = require('../controllers/boxController');
const itemController = require('../controllers/itemController');

router.post('/boxes', boxController.createBox);
router.post('/boxes/:id/items', itemController.loadItems);
router.get('/boxes/:id/items', itemController.getLoadedItems);
router.get('/boxes/available', boxController.getAvailableBoxes);
router.get('/boxes/:id/battery', boxController.getBatteryLevel);

module.exports = router;