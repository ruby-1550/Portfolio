import React, { useState } from "react";
import { supabase } from "../client";
import { useNavigate } from "react-router-dom";

const AddCreator: React.FC = () => {
    const [name, setName] = useState("");
    const [url, setUrl] = useState("");
    const [description, setDescription] = useState("");
    const [imageURL, setImageURL] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        const { error } = await supabase.from("creators").insert([
            { name, url, description, imageURL },
        ]);

        if (error) {
            console.error("Error inserting creator:", error);
        } else {
            navigate("/");
        }
    };

    return (
        <div className="form-container">
            <h2>Add a New Creator</h2>
            <form onSubmit={handleSubmit} className="flex flex-col gap-2">
                <input
                    type="text"
                    placeholder="Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    required
                />
                <input
                    type="url"
                    placeholder="URL"
                    value={url}
                    onChange={(e) => setUrl(e.target.value)}
                    required
                />
                <textarea
                    placeholder="Description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    required
                />
                <input
                    type="text"
                    placeholder="Image URL"
                    value={imageURL}
                    onChange={(e) => setImageURL(e.target.value)}
                />
                <button type="submit" className="primary">Add Creator</button>
            </form>
        </div>
    );
};

export default AddCreator;
