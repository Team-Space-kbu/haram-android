package com.space.shared.data.rothem

enum class ReservationStatus(val text: String) {
    PASS("예약을 정상적으로 완료했습니다."),
    POLICY("필수 동의가 선택되지 않았습니다."),
    PHONE("핸드폰 형식이 맞지 않습니다."),
    TIME("시간을 선택해주세요"),
    DAYS("날짜를 선택해주세요"),
    NAME("이름을 입력해주세요"),
    ERROR("알 수 없는 오류가 발생했습니다."),
    EXIST("이미 예약된 정보가 존재합니다."),
    HOST("인터넷을 연결할 수 없습니다.")
}