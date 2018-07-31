function sortByQId(a, b) {
  return a.qId - b.qId;
}

function sortByCId(a, b) {
  return a.cId - b.cId;
}

function sortQuestion(jsonArray){
  return jsonArray.sort(sortByQId);
}

function sortChoice(jsonArray){
  for(var i=0; i<jsonArray.length; i++){
    jsonArray[i].choice.sort(sortByCId);
  }
  return jsonArray;
}


function sort(jsonArray){
  var temp = sortQuestion(jsonArray);
  return sortChoice(temp);
}

module.exports = {
  sort: sort
}

