const baseUrl = 'http://localhost:8888';
// function for show main content after register user and register user
$('#sign_up').on('submit', (e) => {
    e.preventDefault();
    let form = new FormData(e.target)
    let data = JSON.stringify(Object.fromEntries(form))
    console.log(data)
    axios.post(baseUrl + '/register', data, {
        cache: 'no-cache',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            console.log(response.status)
        })
    window.location.href = 'http://127.0.0.1:5501/57/index.html'
})


// function for login in MicroGramm with localStorage
const signIn = $('#sign_in')

$('#sign_in').on('submit', e => {
    e.preventDefault();
    let form = new FormData(e.target)

    let user = {
        username: form.get('username'),
        password: form.get('password')
    }

    localStorage.setItem('user', JSON.stringify(user))
    searchUser().then(data => console.log(data))

    if(localStorage.getItem('user')==null){
        window.location.href = 'http://127.0.0.1:5501/57/register.html'
    }else{
        window.location.href = 'http://127.0.0.1:5501/57/index.html'
    }

})

let getAuth = function () {
    let userFormStorage = localStorage.getItem('user')
    let userJson = JSON.parse(userFormStorage)
    return userJson;
}

let searchUser = async function () {
    let userAuth = getAuth()

    let user = {
        username: userAuth.username,
        password: userAuth.password
    };
    let searchedUser = await fetch('http://localhost:8888/login', {
        method: 'post',
        // mode:'cors',
        headers: {
            'Authorization': 'Basic ' + window.btoa(user.username + ':' + user.password),
        }
    })
        .then(response => {
            return response.json()
        })
        .then(data => searchedUser = data)
    return searchedUser
}