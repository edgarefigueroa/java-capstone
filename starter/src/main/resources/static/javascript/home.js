const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
const submitForm = document.getElementById("cocktail-form")
const cocktailContainer = document.getElementById("cocktail-container")

let cocktailBody = document.getElementById(`cocktail-body`)
let updateCocktailBtn = document.getElementById('update-cocktail-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/cocktails/"

const handleSubmit = async (e) => {
    e.preventDefault()
    let name = document.getElementById("cocktail-name")
    let type = document.getElementById("cocktail-type")
    let recipe = document.getElementById("cocktail-input")

    let bodyObj = {
//        cocktailName: document.getElementById("cocktail-name").value
//        body: document.getElementById("cocktail-input").value
        cocktailName: name.value,
        cocktailType: type.value,
        body: recipe.value

    }
    await addCocktail(bodyObj);
//    document.getElementById("cocktail-name").value = ''
//    document.getElementById("cocktail-input").value = ''
    name.value = ''
    type.value = ''
    recipe.value = ''
}

async function addCocktail(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
       // cocktailName: JSON.stringify(obj),
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getCocktails(userId);
    }
}

async function getCocktails(userId) {
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createCocktailCards(data))
        .catch(err => console.error(err))
}

async function handleDelete(cocktailId){
    await fetch(baseUrl + cocktailId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getCocktails(userId);
}

async function getCocktailById(cocktailId){
    await fetch(baseUrl + cocktailId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleCocktailEdit(cocktailId){
    let bodyObj = {
        id: cocktailId,
//        name: cocktailName.value,
//        type: cocktailType.value,
        body: cocktailBody.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getCocktails(userId);
}

const createCocktailCards = (array) => {
    cocktailContainer.innerHTML = ''
    array.forEach(obj => {
        let cocktailCard = document.createElement("div")
        cocktailCard.classList.add("m-2")
        cocktailCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="title">Name: ${obj.cocktailName}
                    <br>Type: ${obj.cocktailType}
                    <br>Recipe: ${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getCocktailById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#cocktail-edit-modal">Edit</button>
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

const populateModal = (obj) =>{
//    cocktailName.innerText = ''
//    cocktailName.innerText = obj.name
//    cocktailType.innerText = ''
//    cocktailType.innerText = obj.type
    cocktailBody.innerText = ''
    cocktailBody.innerText = obj.body
    updateCocktailBtn.setAttribute('data-cocktail-id', obj.id)
}

getCocktails(userId);

submitForm.addEventListener("submit", handleSubmit)

updateCocktailBtn.addEventListener("click", (e)=>{
    let cocktailId = e.target.getAttribute('data-cocktail-id')
    handleCocktailEdit(cocktailId);
})