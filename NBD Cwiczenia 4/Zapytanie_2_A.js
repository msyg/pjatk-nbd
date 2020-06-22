printjson(db.people.aggregate([
{$unwind: {
  path: "$credit"
}}, {$project: {
  "credit.currency": "$credit.currency",
  "credit.balance": {$toDouble: "$credit.balance"}
}}, {$group: {
  _id: "$credit.currency",
  totalBalance: {
    $sum: "$credit.balance"
  }
}}]).toArray())
