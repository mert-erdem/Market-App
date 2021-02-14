# Market-App
Software Design lesson final project. </br>
An android e-commerce application prototype (without a database) that developed with Java. </br>

## Design patterns that used in project
- Singleton
- Proxy
- Observer
- Decorator
- Strategy

## Reasons of their usages
- **Singleton**: Only one user can use the application on the current device and a user has only one basket.
- **Proxy**: Only users who has seller license and have their account approved can sell.
- **Observer**: Users should be informed by the system when the prices in their basket change.
- **Decorator**: Users may want to receive from different communication channels (mail, sms, etc.) when their prices in their basket change.
- **Strategy**: Users can choose one of multiple payment methods (credit card, cash on delivery, etc.) to pay for the order, and change this selection at runtime (before completing the purchase).
