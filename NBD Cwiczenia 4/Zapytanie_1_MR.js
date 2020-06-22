var mapWeight = function() {
   emit(this.sex, parseFloat(this.weight));
};

var reduceWeight = function(key, value) {
return Array.avg(value)
};



db.people.mapReduce(
  mapWeight,
  reduceWeight,
  {out:"mr1Weight"}
)

var mapHeight = function() {
   emit(this.sex, parseFloat(this.height));
};

var reduceHeight = function(key, value) {
return Array.avg(value)
};



db.people.mapReduce(
  mapHeight,
  reduceHeight,
  {out:"mr1Height"}
)


printjson(db.mr1Weight.find().toArray())
printjson(db.mr1Height.find().toArray())
