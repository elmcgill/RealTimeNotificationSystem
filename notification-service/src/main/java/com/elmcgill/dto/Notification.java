package com.elmcgill.dto;

import java.util.Set;

public record Notification(NotificationType type, Set<Long> recipients) {
}
