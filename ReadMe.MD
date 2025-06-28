http://localhost:8761/eureka/apps/PRODUCT-SERVICE

#### checking route on api gateway : http://localhost:8001/actuator/gateway/routes

#### checking all application running on eureka : http://localhost:8761/eureka/apps


###### checking health of service 

    management:
    endpoints:
    web:
    exposure:
    include: '*'

