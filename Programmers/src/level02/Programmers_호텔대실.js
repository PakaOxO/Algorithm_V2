/**
 * Programmers_호텔 대실
 *  1. 문제 분류 : 정렬, 그리디
 *  2. 접근 방법
 *    - 시작 시간을 기준으로 정렬
 *    - 사용 중인 방을 담을 배열을 따로 만든 뒤, 배열 안에서 이번에 넣을 시작 시간보다 퇴실 시간이 작은 방들을 전부 pop
 *    -> 시간이 생각보다 오래 걸림
 *      -> 다른 풀이를 참고해 새로 풀이
 *      -> 시간은 분 단위로 수정하여 시간 * 60 + 분으로 표현 가능 -> 최대 0분부터 23시 59분에 청소시간 10분을 더한 시간까지 범위 가능
 *      -> 모든 예약에 대해서 시작시간부터 퇴실시간까지 반복하면서 해당 기간(분)에 +1
 *      -> 전체 시간 배열의 각 인덱스(분)에 대한 숫자가 그 시간에 빌려주어야 할 방의 개수 -> 최대값을 리턴하면 정답
 */
const solution = (book_time) => {
  const dp = Array.from({ length: getTimeStamp("23:59") + 10 }, () => 0);

  /* 메인 로직 */
  book_time.forEach((t) => {
    const [s, e] = [getTimeStamp(t[0]), getTimeStamp(t[1]) + 9];
    for (let i = s; i < e + 1; i++) {
      dp[i]++;
    }
  });

  return Math.max(...dp);

  /* 메서드 */
  function getTimeStamp(t) {
    const [h, m] = t.split(":").map((t) => +t);
    return h * 60 + m;
  }
};

console.log(
  solution([
    ["15:00", "17:00"],
    ["16:40", "18:20"],
    ["14:20", "15:20"],
    ["14:10", "19:20"],
    ["18:20", "21:20"],
  ])
);
console.log(
  solution([
    ["09:10", "10:10"],
    ["10:20", "12:20"],
  ])
);
console.log(
  solution([
    ["10:20", "12:30"],
    ["10:20", "12:30"],
    ["10:20", "12:30"],
  ])
);
