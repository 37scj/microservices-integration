var amqp = require('amqplib');

function sendDrone(drone) {

    const msg = JSON.stringify(drone);

    const sendMessage = function (ch) {

        const q = 'queue.producer.mba';
        const ex = 'projeto.mba.fiap';

        var ok = ch.assertExchange(ex, 'direct', {
            durable: true
        });

        return ok.then(function (_qok) {
            // NB: `sentToQueue` and `publish` both return a boolean
            // indicating whether it's OK to send again straight away, or
            // (when `false`) that you should wait for the event `'drain'`
            // to fire before writing again. We're just doing the one write,
            // so we'll ignore it.

            let r = ch.publish(ex, q, Buffer.from(msg));
            console.log(" [x] Sent %s:'%s'", 'info', msg, r);

            return ch.close();
        });
    };

    return new Promise((resolve, reject)=>
        amqp.connect('amqp://localhost:5672').then(function (conn) {
        return conn.createChannel()
            .then(sendMessage)
            .finally(function () {
                conn.close();
                resolve(msg);
            });
    }).catch(err=>{
        console.warn(err);
        reject(err);
    }));

};

export default { sendDrone }