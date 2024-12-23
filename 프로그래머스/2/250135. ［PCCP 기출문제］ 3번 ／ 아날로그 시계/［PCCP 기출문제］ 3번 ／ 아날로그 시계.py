import math

def solution(h1, m1, s1, h2, m2, s2):
    start_time = h1*3600 + m1*60 + s1
    end_time = h2*3600 + m2*60 + s2
    
    ratio_ms = 3600.0 / 59.0
    k_min_ms = math.ceil(start_time/ratio_ms)
    k_max_ms = math.floor(end_time/ratio_ms)
    
    ms_list = []
    for k in range(k_min_ms, k_max_ms + 1):
        if k < 0:
            continue
        t = ratio_ms * k
        
        if start_time <= t <= end_time:
            ms_list.append(t)
    
    ratio_hs = 43200.0 / 719.0
    k_min_hs = math.ceil(start_time / ratio_hs)
    k_max_hs = math.floor(end_time / ratio_hs)
    
    hs_list = []
    for k in range(k_min_hs, k_max_hs + 1):
        if k < 0:
            continue
        t = ratio_hs * k
        if start_time <= t <= end_time:
            hs_list.append(t)
    
    all_times = ms_list + hs_list
    all_times.sort()
    
    merged = []
    eps = 1e-7
    for t in all_times:
        if not merged:
            merged.append(t)
        else:
            if abs(merged[-1] - t) < eps:
                pass
            else:
                merged.append(t)
    
    
    return len(merged)