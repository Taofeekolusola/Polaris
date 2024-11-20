This API provides functionality to manage boxes, items, and their states. The API supports creating boxes, loading items into boxes, and fetching available boxes. Data is stored in a store.json file, which persists across server restarts.

* The code runs on port 3500
Here are the APIs
1: POST request localhost:3500/api/boxes

Example:
{
    "txref": "BOX12345",
    "batteryCapacity": 80
}

Expected Outcome: 201

"boxes": [
    {
      "id": 1,
      "txref": "BOX12345",
      "weightLimit": 500,
      "batteryCapacity": 80,
      "state": "LOADING"
    }
  ],

  2: POST request localhost:3500/api/boxes/1/items

  Example: 

  {
  "items": [
    { "name": "item1", "weight": 200, "code": "ITEM_001" },
    { "name": "item2", "weight": 100, "code": "ITEM_002" }
  ]
}

Expected Outcome: 201

"items": [
    {
      "name": "item1",
      "weight": 200,
      "code": "ITEM_001",
      "boxId": 1
    },
    {
      "name": "item2",
      "weight": 100,
      "code": "ITEM_002",
      "boxId": 1
    }
  ]

3: GET request to return the available items

  GET /api/boxes/1/items

4: GET request to return the available boxes

GET /api/boxes/available

5: GET request to return the battery level

GET /api/boxes/1/battery

Author
Name: Olusola Taofeek
Role: Backend Dev