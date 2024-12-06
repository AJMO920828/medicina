<template>
  <v-card>
    <v-card-title>Citas Medicas</v-card-title>
    <v-form ref="form">
      <v-card>
        <v-card-title>Filtros</v-card-title>
        <v-card-item>
          <br/>
          <v-row>
            <v-col cols="4">
              <v-select v-model="filtro.doctor.idDoctor" label="Selecciona Doctor" color="primary"
              :items="doctores" item-value="idDoctor" item-title="nombre" variant="outlined"></v-select>
            </v-col>
            <v-col  cols="4">
              <v-select v-model="filtro.consultorio.idConsultorio" label="Selecciona Consultorio" color="primary"
              :items="consultorios" item-value="idConsultorio" item-title="numeroConsultorio" variant="outlined"></v-select>
            </v-col>
            <v-col  cols="4">
              <v-date-picker v-model="filtro.fechaConsulta" color="primary" ></v-date-picker>
            </v-col>
          </v-row>
        </v-card-item>
        <v-card-actions>
          <v-btn @click="buscaCitas(filtro)" color="primary">Buscar</v-btn>
          <v-btn @click="resetValidation" color="Danger">Limpiar</v-btn>
        </v-card-actions>
      </v-card>
    </v-form>
    <br/><br/>
    <v-row>
      <v-col>
        <v-dialog max-width="800" v-model="dialog">
          <template v-slot:activator="{ props: activatorProps }">
            <v-btn v-bind="activatorProps" color="primary" text="Agregar Cita" variant="flat" ></v-btn>
          </template>
        
          <template v-slot:default="{ isActive }">
            <v-card title="Dialog">
              <v-form ref="formDialog">
                <v-card-text>
                  <v-row>
                    <v-col cols="6">
                      <v-select v-model="cita.doctor.idDoctor" label="Selecciona Doctor" color="primary"
                      :items="doctores" item-value="idDoctor" item-title="nombre" variant="outlined"></v-select>
                    </v-col>
                    <v-col  cols="6">
                      <v-select v-model="cita.consultorio.idConsultorio" label="Selecciona Consultorio" color="primary"
                      :items="consultorios" item-value="idConsultorio" item-title="numeroConsultorio" variant="outlined"></v-select>
                    </v-col>
                    <v-col  cols="6">
                      <v-date-picker v-model="cita.fechaConsulta" color="primary" ></v-date-picker>
                    </v-col>
                    <v-col cols="6">
                      <v-time-picker v-model="cita.horarioConsulta" format="24hr"></v-time-picker>
                    </v-col>
                    <v-col cols="6">
                      <v-text-field v-model="cita.nombrePaciente" label="Paciente" color="primary" variant="outlined"></v-text-field>
                    </v-col>
                  </v-row>
                </v-card-text>
              </v-form>
        
              <v-card-actions>
                <v-spacer></v-spacer>
        
                <v-btn text="Cancelar" @click="cerrarDialog()" color="red" ></v-btn>
                <v-btn text="Guardar" @click="GuardarCita()" color="primary" ></v-btn>
              </v-card-actions>
            </v-card>
          </template>
        </v-dialog>
      </v-col>
    </v-row>
    <br/><br/>
    <v-card-item>
      <v-data-table
        :headers="headers"
        :items="citas"
        :search="search"
      ></v-data-table>
    </v-card-item>
  </v-card>
</template>
<script>
import CitasService from '@/services/citas.service'
import DoctoresService from '@/services/doctores.service'
import ConsultoriosService from '@/services/consultorios.service'
export default {
  data () {
    return {
      search: '',
      headers: [
        {
          align: 'start',
          key: 'nombreDoctor',
          sortable: false,
          title: 'Doctor',
        },
        { key: 'paciente', title: 'Paciente' },
        { key: 'horario', title: 'Horario' },
        { key: 'fechaConsulta', title: 'Fecha consulta' },
        { key: 'numeroConsultorio', title: 'Consultorio' },
        { key: 'piso', title: 'piso' },
      ],
      citas:[],
      doctores:[],
      consultorios:[],
      filtro:{doctor:{}, consultorio:{}},
      cita:{doctor:{}, consultorio:{}},
      dialog: false
    }
  },
  mounted() {
    this.buscaCitas({});
    this.obtenerDoctores();
    this.obtenerConsultorios();
  },
  methods: {
    buscaCitas(filtro){
      CitasService.getCitasAll(filtro).then(resp =>{ 
        this.citas = resp.data.body
      }).catch(error=>{
        console.log(error);
      })
    },
    obtenerDoctores(){
      DoctoresService.getDoctoresAll().then(resp=>{
        this.doctores = resp.data.body
      }).catch(error=>{
        console.log(error);
      })
    },
    obtenerConsultorios(){
      ConsultoriosService.getConsultoriosAll().then(resp=>{
        this.consultorios = resp.data.body
      }).catch(error=>{
        console.log(error);
      })
    },
    resetValidation () {
      this.filtro = {doctor:{}, consultorio:{}}
      this.$refs.form.reset()
      this.$refs.form.resetValidation()
      this.buscaCitas({});
    },
    GuardarCita(){
      CitasService.guardarCita(this.cita).then(resp =>{ 
        if(resp.data.status.success){
          this.citas = resp.data.body
          cerrarDialog()
        } else {
          let aux = true; 
          if(resp.data.body.fechaConsulta){
            alert(resp.data.body.fechaConsulta)
            aux = false
          }
          if(resp.data.body.nombrePaciente){
            alert(resp.data.body.nombrePaciente)
            aux = false
          }
          if(resp.data.body.horarioConsulta){
            alert(resp.data.body.horarioConsulta)
            aux = false
          }
          if(aux && resp.data.body){
            alert(resp.data.body);
          }
        }
        
      }).catch(error=>{
        console.log(error);
      })
    },
    cerrarDialog(){
      this.cita = {doctor:{}, consultorio:{}}
      this.$refs.formDialog.reset()
      this.$refs.formDialog.resetValidation()
      this.buscaCitas({});
      
      this.dialog = false
    }
  }
}
</script>
