import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import RestaurantClient from "../api/restaurantClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class RestaurantPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetRestaurants', 'renderRestaurant', 'onGetRestaurantById', 'generateRestaurants'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-restaurants').addEventListener('submit', this.onGetRestaurants);
        document.getElementById('get-by-restaurant-id').addEventListener('submit', this.onGetRestaurant);
        document.getElementById('generate-restaurants').addEventListener('submit', this.generateRestaurants);
        this.client = new RestaurantClient();

        this.dataStore.addChangeListener(this.renderRestaurant)
        this.onGetRestaurants();
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderRestaurant() {
        let resultArea = document.getElementById("restaurant-info");

        const restaurants = this.dataStore.get("restaurants");

        if (restaurants) {
            let restaurantHTML = "<ul>";
            for (let restaurant of restaurants) {
                restaurantHTML += `<li>
                <h5>Restaurant Name: ${restaurant.restaurantName}</h5>
                <div>Restaurant ID: ${restaurant.restaurantId}</div>
                <div>Restaurant Rating: ${restaurant.rating}</div>
                </li>`;
            }
            resultArea.innerHTML = restaurantHTML + "</ul>";
        } else {
            resultArea.innerHTML = "Loading...";
        }

        let resultArea2 = document.getElementById("result-info");

        const restaurant = this.dataStore.get("restaurant");

        if (restaurant) {
            resultArea.innerHTML = `
                <h5>Restaurant Name: ${restaurant.restaurantName}</h5>
                <div>Restaurant ID: ${restaurant.restaurantId}</div>
                <div>Restaurant Rating: ${restaurant.rating}</div>
                <div>Status: ${restaurant.status}</div>
                <div>Type of Cuisine: ${restaurant.cuisine}</div>
                <div>Location: ${restaurant.location}</div>
                <div>Menu: ${restaurant.menu}</div>
            `;
            let menuHTML = "<ul>";
            for (let item of restaurant.menu) {
                menuHTML += `<li>
                <div>item<div>
                </li>`;
            }
            resultArea.innerHTML += menuHTML + "</ul>"
        } else {
            resultArea2.innerHTML = "No Restaurant Selected";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetRestaurants() {
        let result = await this.client.getAllRestaurants(this.errorHandler);
        this.dataStore.set("restaurants", result);
    }

    async onGetRestaurantById(event) {
        event.preventDefault();

        let restaurantId = document.getElementById('restaurant-id').value;
        let result = await this.client.getRestaurant(restaurantId, this.errorHandler);
        this.dataStore.set("restaurant", result);
    }

    async generateRestaurants(event) {
        event.preventDefault();

        const restTest = await this.client.createRestaurant('2', '5', 'name', 'status', 'cuisine', 'location', 'menu');
        const restaurant1 = await this.client.createRestaurant('1','5', 'McBonalds', 'OPEN', 'Indian', 'New York, NY', ['Tandoori Chicken', 'Lamb Curry', 'Chicken Tikka Masala']);
        //this.dataStore.set("restaurants", restaurant1);
        const restaurant2 = await this.client.createRestaurant('2','4', 'KBBQ All You Can Eat', 'OPEN', 'Korean', 'Brooklyn, NY', ['Pork Belly', 'Prime Short Rib', 'Beef Short Ribs']);
        //this.dataStore.set("restaurants", restaurant2);
        const restaurant3 = await this.client.createRestaurant('3','4', 'KBBQ All You Can Eat', 'OPEN', 'Korean', 'Brooklyn, NY', ['Pork Belly', 'Prime Short Rib', 'Beef Short Ribs']);
        //this.dataStore.set("restaurants", restaurant3);
        const restaurant4 = await this.client.createRestaurant('4','4', 'KBBQ All You Can Eat', 'OPEN', 'Korean', 'Brooklyn, NY', ['Pork Belly', 'Prime Short Rib', 'Beef Short Ribs']);
        //this.dataStore.set("restaurants", restaurant4);
        const restaurant5 = await this.client.createRestaurant('5','4', 'KBBQ All You Can Eat', 'OPEN', 'Korean', 'Brooklyn, NY', ['Pork Belly', 'Prime Short Rib', 'Beef Short Ribs']);
        //this.dataStore.set("restaurants", restaurant5);
        const restaurant6 = await this.client.createRestaurant('6','4', 'KBBQ All You Can Eat', 'OPEN', 'Korean', 'Brooklyn, NY', ['Pork Belly', 'Prime Short Rib', 'Beef Short Ribs']);
        //this.dataStore.set("restaurants", restaurant6);
        const restaurant7 = await this.client.createRestaurant('7','4', 'KBBQ All You Can Eat', 'OPEN', 'Korean', 'Brooklyn, NY', ['Pork Belly', 'Prime Short Rib', 'Beef Short Ribs']);
        //this.dataStore.set("restaurants", restaurant7);
        const restaurant8 = await this.client.createRestaurant('8','4', 'KBBQ All You Can Eat', 'OPEN', 'Korean', 'Brooklyn, NY', ['Pork Belly', 'Prime Short Rib', 'Beef Short Ribs']);
        //this.dataStore.set("restaurants", restaurant8);
        const restaurant9 = await this.client.createRestaurant('9','4', 'KBBQ All You Can Eat', 'OPEN', 'Korean', 'Brooklyn, NY', ['Pork Belly', 'Prime Short Rib', 'Beef Short Ribs']);
        //this.dataStore.set("restaurants", restaurant9);
        const restaurant10 = await this.client.createRestaurant('10','4', 'KBBQ All You Can Eat', 'OPEN', 'Korean', 'Brooklyn, NY', ['Pork Belly', 'Prime Short Rib', 'Beef Short Ribs']);
        //this.dataStore.set("restaurants", restaurant10);

        this.onGetRestaurants;
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const restaurantPage = new RestaurantPage();
    restaurantPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
