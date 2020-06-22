var mapFunction = function() {
   emit(this.job, 1);

};


var reduceFunction = function(key, value) {
Array.sum(value);
};



printjson(db.people.mapReduce(
  mapFunction,
  reduceFunction,
  {out:{inline:1}}
))
