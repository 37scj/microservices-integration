import React, { useEffect, useState } from 'react';
import Drone from '../../components/drone/Drone';
import { Container, Grid } from '@material-ui/core';
import droneService from '../../services/droneService';

function NovoDrone(props) {
  const [nome, setNome] = useState("");
  function add() {
    droneService.newDrone(nome)
      .then((r) => {
        if (r.ok) {
          props.fetchDrones && props.fetchDrones();
        }
      })
      .catch(error => console.log(error));
  }

  return (<Container container="true" style={{padding:'30px'}}>
    <input value={nome} onChange={e => setNome(e.target.value)}></input>
    <button className="add" onClick={() => add()}>Add Drone</button>
  </Container>);
}

function Home() {

  const [drones, setDrones] = useState([]);

  function fetchDrones() {
    droneService.getAllDrones()
      .then(d => d.json().then(d => setDrones(d || [])))
      .catch(error => console.log(error));
  }

  // Nota: O array [] deps vazio significa este useEffect será executado uma vez
  // semelhante ao componentDidMount()
  useEffect(() => fetchDrones(), []);

  return (
    <div className="Home">
 
      <Grid container xs={12} item={true} spacing={1}>
        <NovoDrone fetchDrones={() => fetchDrones()} />
      </Grid>
     
      {/* <Drone id={1} name="Drone fixo"/> */}
      <Container maxWidth="lg">
        <Grid container xs={12}  item={true} spacing={1} justify="space-around">
          {drones.map((drone) =>
            <Grid item key={drone.id} item xl={4} style={{ maxWidth: '330px'}}>
              <Drone {...drone} fetchDrones={() => fetchDrones()}/>
            </Grid>
          )}
        </Grid>
      </Container>
    </div >
  );
}

export default Home;
