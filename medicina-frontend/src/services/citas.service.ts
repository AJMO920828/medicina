import axios from 'axios'

class CitasService {
    getCitasAll (filtro:any) {
        return axios.post('http://localhost:8082/api/citas/consultaCitas',filtro)
    }
    guardarCita(cita:any) {
        return axios.post('http://localhost:8082/api/citas/generarCita', cita)
    }
}
export default new CitasService();