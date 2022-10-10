const cookieArr = document.cookie.split("=")
const cookieArr1 = document.cookie.split("=")
const userId = cookieArr[1];
const cocktailId = cookieArr1[1];
//const submitForm = document.getElementById("cocktail-form")
const cocktailContainer = document.getElementById("cocktail-container")

let cocktailBody = document.getElementById(`cocktail-body`)
//let updateCocktailBtn = document.getElementById('update-cocktail-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/cocktails/"


async function getAllCocktails() {
    await fetch(baseUrl + "all/cocktails", {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createCocktailCards(data))
        .catch(err => console.error(err))
}



const createCocktailCards = (array) => {
    cocktailContainer.innerHTML = ''
    array.forEach(obj => {
        let cocktailCard = document.createElement("div")
        cocktailCard.classList.add("m-2")
        cocktailCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.cocktailName}</p>
                    <p class="card-text">${obj.cocktailType}</p>
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="invalid">Delete</button>
                        <button onclick="invalid" type="button" class="btn btn-primary">Edit</button>
                    </div>
                </div>
            </div>
        `
        cocktailContainer.append(cocktailCard);
    })
}
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

getAllCocktails();