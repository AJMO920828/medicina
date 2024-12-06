import axios from 'axios'

class DoctoresService {
    getDoctoresAll () {
        return axios.get('http://localhost:8082/api/doctores')
    }
}
export default new DoctoresService();