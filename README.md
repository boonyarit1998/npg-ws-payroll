# npg-ws-payroll

## 📡 API Endpoints (ตัวอย่าง)
```
# Employees
GET    /api/v1/employee
GET    /api/v1/employee/{id}
POST   /api/v1/employee
PUT    /api/v1/employee/{id}
DELETE /api/v1/employee/{id}

# Exchange Rates
POST    /api/v1/exchange-rates/upload
GET     /api/v1/exchange-rates
GET     /api/v1/exchange-rates/{date}
DELETE  /api/v1/exchange-rates/{date}

# Payroll
POST   /api/v1/payrolls/calculate
GET    /api/v1/payrolls
GET    /api/v1/payrolls/{month}
GET    /api/v1/payrolls/employeeId/{employeeId}
GET    /api/v1/payrolls/employeeId/{employeeId}/{month}

```

## 💻 ตัวอย่าง Code Structure
```
src/main/java/com/company/payroll/
├── config/
│   ├── SecurityConfig.java
│   ├── SchedulerConfig.java
│   └── RedisConfig.java
├── controller/
│   ├── EmployeeController.java
│   ├── PayrollController.java
│   └── ExchangeRateController.java
├── service/
│   ├── EmployeeService.java
│   ├── PayrollService.java
│   └── ExchangeRateService.java
├── repository/
│   ├── EmployeeRepository.java
│   ├── PayrollRepository.java
│   └── ExchangeRateRepository.java
├── model/
│   ├── Employee.java
│   ├── Payroll.java
│   └── ExchangeRate.java
├── dto/
│   ├── EmployeeDTO.java
│   ├── PayrollRequest.java
│   └── PayrollResponse.java
├── scheduler/
│   └── PayrollScheduler.java
├── client/
│   └── ExchangeRateClient.java
└── exception/
    └── GlobalExceptionHandler.java