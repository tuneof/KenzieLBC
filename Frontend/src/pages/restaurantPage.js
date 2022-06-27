import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import RestaurantClient from "../api/restaurantClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class RestaurantPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetRestaurants', 'renderRestaurant'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
//        document.getElementById('get-restaurants').addEventListener('submit', this.onGet);
//        document.getElementById('get-by-restaurant-id').addEventListener('submit', this.onGet);
        this.client = new RestaurantClient();

        this.dataStore.addChangeListener(this.renderRestaurant)
        this.onGetRestaurants();
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderRestaurants() {
        let resultArea = document.getElementById("result-info");

        const restaurants = this.dataStore.get("restaurants");

        if (restaurants) {
            let restaurantHTML = "<ul>";
            for (let restaurant of restaurants) {
                restaurantHTML += `<li>
                <h3>${restaurant.restaurantId}</h3>
                <h4>${restaurant.restaurantName}</h4>
                <h4>${restaurant.rating}</h4>
                <h4>${restaurant.status}</h4>
                <h4>${restaurant.cuisine}</h4>
                <h4>${restaurant.location}</h4>
                <h4>${restaurant.menu}</h4>
                </li>`;
            }
            resultArea.innerHTML = restaurantHTML + "</ul>";
        } else {
            resultArea.innerHTML = "No Restaurants";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetRestaurants() {
        let result = await this.client.getAllRestaurants(this.errorHandler);
        this.dataStore.set("restaurants", result);
    }

//    async onCreate(event) {
//        // Prevent the page from refreshing on form submit
//        event.preventDefault();
//        this.dataStore.set("example", null);
//
//        let name = document.getElementById("create-name-field").value;
//
//        const createdExample = await this.client.createExample(name, this.errorHandler);
//        this.dataStore.set("example", createdExample);
//
//        if (createdExample) {
//            this.showMessage(`Created ${createdExample.name}!`)
//        } else {
//            this.errorHandler("Error creating!  Try again...");
//        }
//    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const examplePage = new RestaurantPage();
    restaurantPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
