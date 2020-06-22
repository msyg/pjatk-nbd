var mapFunction = function() {
  var key = this.nationality;
  var calcBMI = function (weight, height) {
    return (weight / Math.pow(height / 100, 2));
  }

  var weight = parseFloat(this.weight);
  var height = parseFloat(this.height);
  var value = { count: 1, sum: calcBMI(weight, height),
    		minBMI: calcBMI(weight, height), maxBMI: calcBMI(weight, height)};

  emit( key, value );

};

var reduceFunction = function (key, values) {
  var reducedObject = { count: 0, sum: 0, minBMI: values[0].minBMI, maxBMI: values[0].maxBMI }

  values.forEach(function (value) {
    reducedObject.count += value.count;
    reducedObject.sum += value.sum;
    reducedObject.minBMI = Math.min(reducedObject.minBMI, value.minBMI);
    reducedObject.maxBMI = Math.max(reducedObject.maxBMI, value.maxBMI);
  });

  return reducedObject;
}

var finalizeFunction = function (key, reducedObject) {
  reducedObject.avgBMI = reducedObject.sum / reducedObject.count;
  delete reducedObject.sum;
  delete reducedObject.count;
  return reducedObject;
}

printjson(db.people.mapReduce(mapFunction, reduceFunction, {
  out: 'mr4',
  finalize: finalizeFunction,
}))


printjson(db.mr4.find().toArray())
