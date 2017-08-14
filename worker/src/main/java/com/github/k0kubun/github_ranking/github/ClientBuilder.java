package com.github.k0kubun.github_ranking.github;

import com.github.k0kubun.github_ranking.dao.AccessTokenDao;
import com.github.k0kubun.github_ranking.model.AccessToken;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.skife.jdbi.v2.DBI;

// This will have the logic to throttle GitHub API tokens.
public class ClientBuilder
{
    private final DBI dbi;

    public ClientBuilder(DataSource dataSource)
    {
        dbi = new DBI(dataSource);
    }

    public GitHubClient buildForUser(Integer userId)
    {
        AccessToken token = dbi.onDemand(AccessTokenDao.class).findByUserId(userId);

        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(token.getToken());
        return client;
    }
}
