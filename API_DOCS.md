# Currency Converter API - Documentation
## Base URL
  http://localhost:8080/api
  
## 1. Get Exchange Rates
  Endpoint :
    GET /api/rates?base={currency}

### Example Request
  GET /api/rates?base=USD
### Example Response
  {
    "EUR": 0.92,
    "GBP": 0.78,
    "INR": 83.2,
    "JPY": 150.3
  }
### Error Responses
  Status Code	Message:
    500	{"error": "External API is unavailable"}
    
## 2. Convert Currency
  Endpoint:
    POST /api/convert
      Request Body (JSON)
          {
            "from": "USD",
            "to": "EUR",
            "amount": 100
          }
### Response
  {
    "from": "USD",
    "to": "EUR",
    "amount": 100,
    "convertedAmount": 92.0
  }
### Error Responses
  Status Code	Message
    400	{"error": "Invalid currency code"}
    400	{"error": "Amount must be positive"}
    500	{"error": "External API is unavailable"}
