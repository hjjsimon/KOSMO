import axios from "axios"

export const fetches = async url =>{
    return await axios.get(url)
                      .then(res=>[...res.data])
                      .catch(e=>console.log(e))
}