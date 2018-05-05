const grpc = require('grpc');

const currencies = {
  'USD': {
    value: 3.5343,
    name: 'American dollar',
    callbacks: [],
  },
  'EUR': {
    value: 4.444,
    name: 'Euro',
    callbacks: [],
  },
};

Object.keys(currencies).forEach((code) => {
  const simulateNewValue = () => {
    currencies[code].value += Math.random() * 0.05;
    currencies[code].callbacks.forEach(callback => callback(currencies[code].value));
    setTimeout(simulateNewValue, 2000 + Math.round(Math.random() * 1000));
  };
  simulateNewValue();
});

try {
  const protoDescriptor = grpc.load(__dirname + '/../protos/currency_exchange/currency_stream.proto');

  const server = new grpc.Server();
  server.addService(protoDescriptor.CurrencyService.service, {
    GetCurrencyValues: (call) => {
      console.log('=> GetCurrencyValues');
      currencies[call.request.code].callbacks.push((value) => {
        call.write({
          value,
        });
      });
    },
    GetAvailableCurrencies: (call, callback) => {
      console.log('=> GetAvailableCurrencies', call, callback);
      callback(null, Object.keys(currencies).map(code => ({
        code,
        value: currencies[code].value,
      })));
    },
  });
  server.bind('0.0.0.0:50051', grpc.ServerCredentials.createInsecure());
  server.start();
} catch (e) {
  console.error(e);
}
