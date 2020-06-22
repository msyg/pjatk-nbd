var mapFunction = function () {
  if (!this.credit) return;

  this.credit.forEach(function (credit) {
    emit(credit.currency, {
      count: 1,
      balance: parseFloat(credit.balance) || 0
    });
  });
};

var reduceFunction = function (key, values) {
  var reducedObject = {
    balance: 0,
    count: 0
  }

values.forEach(function (value) {
    reducedObject.balance += value.balance;
    reducedObject.count += value.count;
  });

  return reducedObject;
}

var finalizeFunction = function (key, reducedObject) {
  reducedObject.avgCreditBalance = (reducedObject.balance / reducedObject.count).toFixed(2);
  reducedObject.totalCreditBalance = reducedObject.balance;
  delete reducedObject.balance;
  delete reducedObject.count;
  return reducedObject;
}

printjson(db.people.mapReduce(mapFunction, reduceFunction, {
  query: {
    "nationality": "Poland",
    "sex": "Female"
  },
  out: "mr5",
  finalize: finalizeFunction
}))

printjson(db.mr5.find().toArray())
