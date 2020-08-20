# Shopping Point

Shopping app to browse up-to-date products list on screen, to add and remove items from cart, and to store wish list items locally in database.

This app incorporated with its level best in 
- MVVM clean architecture.  
- LiveData observables to ensure UI state matches our live data state.
- ViewModel for using UI tasks by lifecycle conscious way and preserving configuration changes.
- Retrofit2 for REST client.
- Room for storage.
- Mockito (one as of now) for unit test one of LiveData observables communication.
- App (written in Java), run and tested manually on Samsung S10 mobile device.

## Prerequisites


- Android Studio 3.0 and above 
  (This app built on latest version by now as Android studio 4.0.1 and Gradle version 6.1.1)
- Android API level 19 and above
- Java SDK version 1.8


## Installation

You can git clone the project using the path

```
https://github.com/Divs-B/ShoppingPoint.git
```
## What this project has

- Single Activity Model with reusable Fragments. 
- Swipe refresh to refresh the page in RecyclerView
- Show all Product Details.
- Add and Remove any product to cart and update total amount.
- Page refresh with updated details like stock etc for added cart items.
- Add any product to wishlist.
- Add any product from wishlist to cart.
- Store wishlist in DB for persistant storage.

## User Stories Covered

-  As a Customer I can view the products and their category, price, old price for discounted products and availability information.
 - As a Customer I can add a product to my shopping cart.
 - As a Customer I can add a product to my wishlist. 
 - As a Customer I can remove a product from my shopping cart. 
 - As a Customer I can view the total price for the products in my shopping cart. 
 - As a Customer I am unable to add Out of Stock products to the shopping cart. 
 - As a Customer I can move a product from my wishlist to the shopping cart.

## User Stories Un Done
 - As a Customer I can remove a product from my wishlist. 