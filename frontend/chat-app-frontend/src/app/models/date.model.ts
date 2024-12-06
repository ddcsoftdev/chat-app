//simulates java LocalDateTime class
export class LocalDateTime {
  private year: number;
  private month: number;
  private dayOfMonth: number;
  private hour: number;
  private minute: number;
  private second: number;
  private nano: number;

  constructor(dateString: string) {
    const date = new Date(dateString);
    this.year = date.getFullYear();
    this.month = date.getMonth() + 1;
    this.dayOfMonth = date.getDate();
    this.hour = date.getHours();
    this.minute = date.getMinutes();
    this.second = date.getSeconds();
    this.nano = Number(dateString.split('.')[1]) || 0;
  }

  // Individual getters
  getYear(): number {
    return this.year;
  }

  getMonth(): number {
    return this.month;
  }

  getDayOfMonth(): number {
    return this.dayOfMonth;
  }

  getHour(): number {
    return this.hour;
  }

  getMinute(): number {
    return this.minute;
  }

  getSecond(): number {
    return this.second;
  }

  getNano(): number {
    return this.nano;
  }

  // Get just the date portion (YYYY-MM-DD)
  getDate(): string {
    return `${this.year}-${String(this.month).padStart(2, '0')}-${String(
      this.dayOfMonth
    ).padStart(2, '0')}`;
  }

  // Get just the time portion (HH:mm:ss)
  getTime(): string {
    return `${String(this.hour).padStart(2, '0')}:${String(
      this.minute
    ).padStart(2, '0')}:${String(this.second).padStart(2, '0')}`;
  }

  // Parse a string into LocalDateTime
  static parse(dateString: string): LocalDateTime {
    return new LocalDateTime(dateString);
  }

  // Convert to string in ISO format with nanoseconds
  toString(): string {
    const dateTime = `${this.getDate()}T${this.getTime()}`;
    return this.nano ? `${dateTime}.${this.nano}` : dateTime;
  }

  //compare to see which one is before
  compareTo(other: LocalDateTime): number {
    if (this.year !== other.year) {
        return this.year - other.year;
    }
    if (this.month !== other.month) {
        return this.month - other.month;
    }
    if (this.dayOfMonth !== other.dayOfMonth) {
        return this.dayOfMonth - other.dayOfMonth;
    }
    if (this.hour !== other.hour) {
        return this.hour - other.hour;
    }
    if (this.minute !== other.minute) {
        return this.minute - other.minute;
    }
    if (this.second !== other.second) {
        return this.second - other.second;
    }
    return this.nano - other.nano;
}
}
