def str_time_to_second(time):
    mm, ss = map(int, time.split(":"))
    return mm*60+ss

def second_time_to_str(time):
    return f'{(time//60):02}:{(time%60):02}'

def solution(video_len, pos, op_start, op_end, commands):
    video_len = str_time_to_second(video_len)
    pos = str_time_to_second(pos)
    op_start = str_time_to_second(op_start)
    op_end = str_time_to_second(op_end)
    if op_start <= pos <= op_end:
        pos = op_end
            
    for command in commands:
        if command=="next":
            pos = min(video_len, pos+10)
        elif command=="prev":
            pos = max(0, pos-10)
        
        if op_start <= pos <= op_end:
            pos = op_end
        print(pos)
    
    return second_time_to_str(pos)