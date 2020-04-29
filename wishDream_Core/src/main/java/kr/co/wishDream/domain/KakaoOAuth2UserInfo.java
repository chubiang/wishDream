package kr.co.wishDream.domain;

import kr.co.wishDream.domain.enums.AuthProvider;

public class KakaoOAuth2UserInfo extends Member {

	private static final long serialVersionUID = 1L;

	public KakaoOAuth2UserInfo() {
	}
	
	protected AuthProvider getAuthProviderEnum() {
		return AuthProvider.KAKAO;
	}

	
	
}
