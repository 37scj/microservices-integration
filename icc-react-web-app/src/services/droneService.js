//import React from 'react';
const fetch = require("isomorphic-fetch");
const isDebug = true;
const urlBaseBackend = '/api';//process.env.MS_ENDPOINT?process.env.MS_ENDPOINT:'http://localhost:8090';

const headers = {
  'Accept': 'application/json, text/plain',
  'Content-Type': 'application/json'
}
const opts = { mode: 'no-cors'};

// mode: 'no-cors', // 'cors' by default
function getAllDrones() {
  const url = `${urlBaseBackend}/drones`;
  return fetch(url, {
    method: 'GET',
    headers: headers,
  }, opts).then(a => {
    if (isDebug) console.log(url, a);
    return a;
  });
}

function getDrone(id) {
  const url = `${urlBaseBackend}/drones/${id}`;
  return fetch(url, {
    method: 'GET',
    headers: headers,
  }, opts).then(a => {
    if (isDebug) console.log(url, a);
    return a;
  });
}
function deleteDrone(id) {
  const url = `${urlBaseBackend}/drones/${id}`;
  return fetch(url, {
    method: 'DELETE',
    headers: headers,
  }, opts).then(a => {
    if (isDebug) console.log(url, a);
    return a;
  });
}

/**
 * Simula o envio de dados do drone. Atualiza os dados
 * @param {Drone} drone 
 */
function saveDrone(drone) {
  const url = `${urlBaseBackend}/drones/${drone.id}`;
  return fetch(url, {
    method: 'PATCH',
    headers: headers,
    body: JSON.stringify(drone)

  }, opts).then(a => {
    if (isDebug) console.log(url, a);
    return a;
  });
}

function newDrone(nome) {
  const url = `${urlBaseBackend}/drones`;
  return fetch(url, {
    method: 'POST',
    headers: headers,
    body: JSON.stringify({ id: null, nome })
  }, opts).then(a => {
    if (isDebug) console.log(url, a);
    return a;
  });
}

export default { getAllDrones, getDrone, deleteDrone, saveDrone, newDrone };
