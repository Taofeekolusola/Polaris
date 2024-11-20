const express = require('express');
const boxRoutes = require('./routes/boxRoutes');


// Express App Initialization
const app = express();

// Middleware for parsing JSON requests
app.use(express.json());

// API Routes
app.use('/api', boxRoutes);

// Error Handling
app.use((err, req, res, next) => {
  console.error(err);
  res.status(500).json({ error: 'Internal server error.' });
});

const PORT = 3500;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));