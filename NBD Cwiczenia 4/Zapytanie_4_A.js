printjson(db.people.aggregate(
   [
     { $project: { _id: 1, nationality: 1, BMI: { $round: [ { $multiply: [ { $divide: [ {$toDouble: "$weight"}, { $pow: [ {$toDouble: "$height"}, 2 ] } ] }, 10000]}, 2 ] } } },
	 { $group: { _id: "$nationality", maxBMI: { $max: "$BMI" }, minBMI: { $min: "$BMI" }, avgBMI: { $avg: "$BMI"} } }
   ]
).toArray())
