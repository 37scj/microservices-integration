//import React from 'react';
const fetch = require("isomorphic-fetch");
const isDebug = true;
const urlBaseBackend = '/producer';

const headers = {
  'Accept': 'application/json, text/plain',
  'Content-Type': 'application/json'
};

/**
 * Simula o envio de dados do drone. Atualiza os dados
 * @param {Drone} drone 
 */
function sendDroneRabbitMQ(drone) {
    const url = `${urlBaseBackend}/send`;
    return fetch(url, {
      method: 'POST',
      headers: headers,
      body: JSON.stringify(drone)
  
    }).then(a => {
      if (isDebug) console.log(url, a);
      return a;
    });
  }

  export default { sendDroneRabbitMQ }





// const fetch = require("isomorphic-fetch");
// const urlAMQP = 'http://localhost:15672/api/exchanges/%2f/projeto.mba.fiap/publish';

// function sendDrone(drone){

//         var myHeaders = new Headers();
//         myHeaders.append("Authorization", "Basic Z3Vlc3Q6Z3Vlc3Q=");
//         myHeaders.append("Content-Type", "application/json");

//         var raw = JSON.stringify({"properties":{},
//                                 "routing_key":"queue.producer.mba",
//                                 "payload":JSON.stringify(drone),
//                                 "payload_encoding":"string"});

//         var requestOptions = {
//         method: 'POST',
//         headers: myHeaders,
//         body: raw,
//         redirect: 'follow'
//         };

//     return fetch(urlAMQP, requestOptions)
// }

// export default {sendDrone}