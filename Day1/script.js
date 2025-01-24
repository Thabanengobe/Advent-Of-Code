const file = require('fs');

const list1 = [];
const list2 = [];
let sum = 0;

const fileData = file.readFileSync('./input.txt', 'utf8');
const locationList = fileData.split("\n");

locationList.forEach(locations =>{
  const locationId = locations.split('  ');
  list1.push(locationId[0]);
  list2.push(locationId[1]);
});

function sectionSort(nums){

  for(let i = 0; i < (nums.length-1); i++){

    let min_value = i;
    for(let j = (i+1); j < nums.length; j++){
      if(nums[j] < nums[min_value]){
        min_value = j;
      }
    }

    if (min_value !== i){
      let temp = nums[min_value];// 3
      nums[min_value] = nums[i]; // 7 => undex 2 = 7
      nums[i] = temp;
    }
  }

  return nums;
}

const sorted1 = sectionSort(list1);
const sorted2 = sectionSort(list2);

sorted1.map((locationId, index)=>{
  let diif = (Number.parseInt(locationId) - Number.parseInt(sorted2[index]))

  if(diif < 0) diif *= -1;
  sum+= diif;
})

let simSum = 0;
sorted1.map((locationId) =>{
  let similarity = 0;
  sorted2.forEach(id =>{
    if(Number.parseInt(locationId) === Number.parseInt(id)) ++similarity
  })
  simSum += (locationId*similarity);
} )

console.log(simSum)