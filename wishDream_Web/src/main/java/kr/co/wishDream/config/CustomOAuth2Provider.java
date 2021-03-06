package kr.co.wishDream.config;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

@Component
public enum CustomOAuth2Provider {
	KAKAO {
		@Override
		public ClientRegistration.Builder getBuilder(String registrationId) {
			ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.POST)
					.scope("profile", "account_email")
					.authorizationUri("https://kauth.kakao.com/oauth/authorize")
					.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
					.tokenUri("https://kauth.kakao.com/oauth/token")
					.userInfoUri("https://kapi.kakao.com/v2/user/me")
					.userNameAttributeName("id")
					.clientName("Kakao");
			return builder;
		}
	};

	private static final String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/login/oauth2/code/{registrationId}";

	protected final ClientRegistration.Builder getBuilder(String registrationId,
															ClientAuthenticationMethod method) {
		ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
		builder.clientAuthenticationMethod(method);
		builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
		builder.redirectUriTemplate(CustomOAuth2Provider.DEFAULT_LOGIN_REDIRECT_URL);
		return builder;
	}

	public abstract ClientRegistration.Builder getBuilder(String registrationId);
}
