 var mapFunction = function() {
    for (var i = 0; i < this.credit.length; i++) {
       var key = this.credit[i].currency;
       var value = parseFloat(this.credit[i].balance);

       emit(key, value);
    }
};

var reduceFunction = function(key, value) {
return Array.sum(value)
};

printjson(db.people.mapReduce(
  mapFunction,
  reduceFunction,
  {out:"mr2"}
))


printjson(db.mr2.find().toArray())

