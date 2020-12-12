//import React from 'react';
const fetch = require("isomorphic-fetch");
const isDebug = true;
const urlBaseBackend = 'http://localhost:8090';
const headers = {
  'Accept': 'application/json, text/plain',
  'Content-Type': 'application/json'
}

// mode: 'no-cors', // 'cors' by default
function getAllDrones() {
  const url = `${urlBaseBackend}/drones`;
  return fetch(url, {
    method: 'GET',
    headers: headers,
  }).then(a => {
    if (isDebug) console.log(url, a);
    return a;
  });
}

function getDrone(id) {
  const url = `${urlBaseBackend}/drones/${id}`;
  return fetch(url, {
    method: 'GET',
    headers: headers,
  }).then(a => {
    if (isDebug) console.log(url, a);
    return a;
  });
}
function deleteDrone(id) {
  const url = `${urlBaseBackend}/drones/${id}`;
  return fetch(url, {
    method: 'DELETE',
    headers: headers,
  }).then(a => {
    if (isDebug) console.log(url, a);
    return a;
  });
}

/**
 * Simula o envio de dados do drone. Atualiza os dados
 * @param {Drone} drone 
 */
function saveDrone(drone) {
  const url = `${urlBaseBackend}/drones`;
  return fetch(url, {
    method: 'PATCH',
    headers: headers,
    body: JSON.stringify(drone)

  }).then(a => {
    if (isDebug) console.log(url, a);
    return a;
  });
}

function newDrone(nome) {
  const url = `${urlBaseBackend}/drones`;
  return fetch(url, {
    method: 'POST',
    headers: headers,
    body: JSON.stringify({ id: 0, nome })
  }).then(a => {
    if (isDebug) console.log(url, a);
    return a;
  });
}

export default { getAllDrones, getDrone, deleteDrone, saveDrone, newDrone };
