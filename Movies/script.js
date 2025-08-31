// Creates constants that can be used later within the code.
const searchInput = document.querySelector("#searchInput");
const searchButton = document.querySelector("#searchButton");
const resultsContainer = document.querySelector("#results");

searchButton.addEventListener("click", () => {
    const searchTerm = searchInput.value.trim();
    if (searchTerm) {
        searchMovies(searchTerm);
    }
});

searchInput.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
        const searchTerm = searchInput.value.trim();
        if (searchTerm) {
            searchMovies(searchTerm);
        }
    }
});

const apiKey = OMDB_KEY
function searchMovies(query) {
    // Express server called
    const url = `/movies?q=${encodeURIComponent(query)}`;
    fetch(url)
        .then(res => {
            if (!res.ok) {
                return res.json().then(errorData => {
                    throw new Error(errorData.message || 'Server error');
                });
            }
            return res.json();
        })
        .then(data => {
            displayMovies(data.Search);
        })
        .catch(err => {
            console.error("API error: ", err);
            resultsContainer.innerHTML = "<p class='text-red-500'>Something went wrong. Try again</p>";
        });
}

function displayMovies(movies) {
    if (!movies) { resultsContainer.innerHTML = "<p class='text-gray-500'>No results found.</p>"; return; } resultsContainer.innerHTML = "";
    movies.forEach(movie => {
        const movieCard = document.createElement("div");
        movieCard.className = "bg-white rounded shadow p-4 text-left";
        movieCard.innerHTML = `
            <h2 class="text-xl font-semibold">${movie.Title}</h2>
            <p><strong>Year:</strong> ${movie.Year}</p>
            <img src="${movie.Poster !== "N/A" ? movie.Poster : "https://via.placeholder.com/150"}" 
                 alt="Poster for ${movie.Title}" 
                 class="mt-2 max-w-xs">
        `;
        resultsContainer.appendChild(movieCard);
    });
}