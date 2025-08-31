import express from "express";
import fetch from "node-fetch";
import dotenv from "dotenv";
import cors from "cors";

dotenv.config();

const app = express();
app.use(cors());

// Static files
app.use(express.static('.'));

// Root route
app.get("/", (req, res) => {
    res.sendFile('index.html', { root: '.' });
});

//Moviews API route
app.get("/movies", async (req, res) => {
    const query = req.query.q;
    if (!query) return res.status(400).json({ error: "Missing query" });

    try {
        const url = `https://www.omdbapi.com/?s=${encodeURIComponent(query)}&apikey=${process.env.OMDB_KEY}`;
        const response = await fetch(url);
        const data = await response.json();

        console.log("API Response:", data); 
        
        // Verify if API key is active
        if (data.Error === "Invalid API key!") {
            console.log("API key is deactivated - check OMDB website");
            return res.status(401).json({ 
                error: "API key issue", 
                message: "The movie database API key needs to be reactivated.",
                apiError: data.Error 
            });
        }

        res.json(data);
    } catch (err) {
        console.error(err);
        res.status(500).json({ error: "Server error" });
    }
});

app.listen(3000, () => console.log("Server running on http://localhost:3000"));
