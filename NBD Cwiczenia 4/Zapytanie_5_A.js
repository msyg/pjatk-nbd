printjson(db.people.aggregate(
   [
     { $match: { nationality: "Poland", sex: "Female"}},
	 { $unwind: { path: "$credit" } },
	 { $group: { _id:"$credit.currency", avgCreditBalance: { $avg: {$toDouble: "$credit.balance"}}, totalCreditBalance: { $sum: {$toDouble: "$credit.balance"}} } }
   ]
).toArray())
