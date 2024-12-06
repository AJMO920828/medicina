import axios from 'axios'

class ConsultoriosService {
    getConsultoriosAll () {
        return axios.get('http://localhost:8082/api/Consultorios')
    }
}
export default new ConsultoriosService();