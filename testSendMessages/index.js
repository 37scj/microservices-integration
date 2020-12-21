#!/usr/bin/env node

var amqp = require('amqplib');

const rnd = (min, max, digits = 8) => Math.round(
    (Math.random() * (max - min) + min) * Math.pow(10, digits)) / Math.pow(10, digits);
const rnd_ceil = (min, max, digits = 2) => {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.round((Math.random() * (max - min + 1) + min) * Math.pow(10, digits)) / Math.pow(10, digits);
};

const sendMessage = function (ch) {
    const q = 'queue.mba.fiap';
    const ex = 'projeto.mba.fiap';
    const msgs = [1,2,3,4,5,6,7,8,9,10].map(id=> JSON.stringify({
        "id": id,
        latitude: -23.533773 + rnd(-2, 2),
        longitude: -46.625290 + rnd(-2, 2),
        temperatura: rnd_ceil(-25, 60),
        umidade: rnd_ceil(0, 99)
    }));

    var ok = ch.assertExchange(ex, 'direct', {
        durable: true
    });

    return ok.then(function (_qok) {
        console.log(_qok);
        // NB: `sentToQueue` and `publish` both return a boolean
        // indicating whether it's OK to send again straight away, or
        // (when `false`) that you should wait for the event `'drain'`
        // to fire before writing again. We're just doing the one write,
        // so we'll ignore it.
        msgs.forEach(msg=>{
            let r=ch.publish(ex, q, Buffer.from(msg));
            console.log(" [x] Sent %s:'%s'", msg, r);
        });
        return ch.close();
    });
};

amqp.connect('amqp://localhost:5672').then(function (conn) {
    return conn.createChannel()
        .then(sendMessage)
        .finally(function () {
        conn.close();
    });
}).catch(console.warn);
