/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.mahout.cf.taste.impl.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.CandidateItemsStrategy;
import org.apache.mahout.cf.taste.recommender.MostSimilarItemsCandidateItemsStrategy;

/**
 * Abstract base implementation for retrieving candidate items to recommend
 */
public abstract class AbstractCandidateItemsStrategy implements CandidateItemsStrategy,
    MostSimilarItemsCandidateItemsStrategy {

  @Override
  public FastIDSet getCandidateItems(long userID, DataModel dataModel) throws TasteException {
    return doGetCandidateItems(dataModel.getItemIDsFromUser(userID), dataModel);
  }

  @Override
  public FastIDSet getCandidateItems(long[] itemIDs, DataModel dataModel) throws TasteException {
    FastIDSet preferredItemIDs = new FastIDSet(itemIDs.length);
    preferredItemIDs.addAll(itemIDs);
    return doGetCandidateItems(preferredItemIDs, dataModel);
  }

  abstract FastIDSet doGetCandidateItems(FastIDSet preferredItemIDs, DataModel dataModel) throws TasteException;
}
