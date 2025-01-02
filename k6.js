import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '1m', target: 1},
        { duration: '1m', target: 1 },
        { duration: '20s', target: 0 },
    ],
};

const params = {
    headers: {
        'Content-Type': 'application/json',
    },
};

/**
 * Insert products into the API
 */ 
const insertProduct = () => {
  let body = {
    title: "some title",
    price: 3000
  };
  let res = http.post('http://localhost:8080/v1/products', JSON.stringify(body), params);
  console.log(res.status);
  check(res, { 'status was 201': (r) => r.status == 201 });
  sleep(1);
}

export default function () { insertProduct() }
