A DoorDash lite application which will go to the web and display the data.

The app has 2 screens (list of restaurants and restaurant detail page)

App is written with Kotlin, MVVM architecture, and uses Hilt dependency injection for viewModels, repository and most of other classes.

App uses retrofit for fetching restaurants from Doordash service: https://api.doordash.com/v1/store_feed/?lat=37.422740&lng=-122.139956&offset=0&limit=5

App uses coroutine for making network request in the background.

Some important classes in the project:

Activities : 
    MainActivity, displays a list of restaurants.
    RestaurantDetailActivity, display a restaurant's detail information, includes rating, distance, and a list of popular items.

ViewModels:
    StoresViewModel, provides a list of stores for MainActivity.
    RestaurantDetailViewModel, provides store details for RestaurantDetailActivity

Repository:
    Repository, single data truth of the app, call DoorDashApi service to fetch stores, provides data for StoresViewModel and RestaurantDetailVidewModel.

Network API:
    DoorDashApi, make call to DoorDash server to fetch stores.
    ResponseHandler, handles different cases of network request responses.


