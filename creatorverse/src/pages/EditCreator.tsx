import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { supabase } from "../client"

const EditCreator = () => {
    const { id } = useParams()
    const navigate = useNavigate()

    const [creator, setCreator] = useState({
        name: "",
        url: "",
        description: "",
        imageURL: ""
    })

    // Fetch creator info on load
    useEffect(() => {
        const fetchCreator = async () => {
            const { data, error } = await supabase
                .from("creators")
                .select("*")
                .eq("id", id)
                .single()

            if (error) console.error(error)
            else setCreator(data)
        }

        fetchCreator()
    }, [id])

    // Update creator
    const handleUpdate = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()

        const { error } = await supabase
            .from("creators")
            .update(creator)
            .eq("id", id)

        if (error) console.error(error)
        else navigate("/")
    }

    // Delete creator
    const handleDelete = async () => {
        const { error } = await supabase
            .from("creators")
            .delete()
            .eq("id", id)

        if (error) console.error(error)
        else navigate("/")
    }

    return (
        <div className="form-container">
            <h2>Edit Creator</h2>
            <form onSubmit={handleUpdate}>
                <input
                    type="text"
                    placeholder="Name"
                    value={creator.name}
                    onChange={(e) => setCreator({ ...creator, name: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="URL"
                    value={creator.url}
                    onChange={(e) => setCreator({ ...creator, url: e.target.value })}
                />
                <textarea
                    placeholder="Description"
                    value={creator.description}
                    onChange={(e) => setCreator({ ...creator, description: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="Image URL"
                    value={creator.imageURL}
                    onChange={(e) => setCreator({ ...creator, imageURL: e.target.value })}
                />

                <button type="submit">Update Creator</button>
            </form>

            <button onClick={handleDelete} style={{ marginTop: "1rem", color: "white", backgroundColor: "#db3131" }}>
                Delete Creator
            </button>
        </div>
    )
}

export default EditCreator
